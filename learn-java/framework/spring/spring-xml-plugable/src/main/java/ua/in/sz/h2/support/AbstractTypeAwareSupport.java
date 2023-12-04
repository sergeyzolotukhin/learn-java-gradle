package ua.in.sz.h2.support;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractTypeAwareSupport<T>
        implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent>, InitializingBean {

    private @Nullable ApplicationContext context;
    private @Nullable Class<T> type;
    private @Nullable BeansOfTypeTargetSource targetSource;
    private Collection<Class<?>> exclusions = Collections.emptySet();

    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Configures the type of beans to be looked up.
     *
     * @param type the type to set
     */
    public void setType(Class<T> type) {
        this.type = type;
    }

    /**
     * Configures the types to be excluded from the lookup.
     *
     * @param exclusions
     */
    public void setExclusions(Class<?>[] exclusions) {
        this.exclusions = Arrays.asList(exclusions);
    }

    /**
     * Returns all beans from the {@link ApplicationContext} that match the given type.
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<T> getBeans() {

        TargetSource targetSource = this.targetSource;

        if (targetSource == null) {
            throw new IllegalStateException("Traget source not initialized!");
        }

        ProxyFactory factory = new ProxyFactory(List.class, targetSource);

        return (List<T>) factory.getProxy();
    }

    @Override
    public void afterPropertiesSet() {
        ApplicationContext context = this.context;
        if (context == null) {
            throw new IllegalStateException("ApplicationContext not set!");
        }

        Class<?> type = this.type;
        if (type == null) {
            throw new IllegalStateException("No type configured!");
        }

        this.targetSource = new BeansOfTypeTargetSource(context, type, false, exclusions);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().equals(context) && targetSource != null) {
            targetSource.freeze();
        }
    }

    /**
     * {@link TargetSource} implementation that returns all beans of the configured type from the
     * {@link ListableBeanFactory} the instance was set up with. Allows freezing the lookup as calls to
     * {@link ListableBeanFactory#getBeansOfType(Class, boolean, boolean)} are potentially expensive as the entire factory
     * has to be scanned for type information.
     *
     * @author Oliver Gierke
     */
    static class BeansOfTypeTargetSource implements TargetSource {

        private final ListableBeanFactory context;
        private final Class<?> type;
        private final boolean eagerInit;
        private final Collection<Class<?>> exclusions;

        private boolean frozen = false;
        private @Nullable Collection<Object> components;

        /**
         * Creates a new {@link BeansOfTypeTargetSource} using the given {@link ListableBeanFactory} to lookup beans of the
         * given type.
         *
         * @param context    must not be {@literal null}.
         * @param type       must not be {@literal null}.
         * @param eagerInit  whether to eagerly init {@link FactoryBean}s, defaults to {@literal false}.
         * @param exclusions which types to exclude from the lookup, must not be {@literal null}.
         */
        public BeansOfTypeTargetSource(ListableBeanFactory context, Class<?> type, boolean eagerInit,
                                       Collection<Class<?>> exclusions) {

            Assert.notNull(context, "ListableBeanFactory must not be null!");
            Assert.notNull(type, "Type must not be null!");
            Assert.notNull(exclusions, "Exclusions must not be null!");

            this.context = context;
            this.type = type;
            this.eagerInit = eagerInit;
            this.exclusions = exclusions;
            this.components = null;
        }

        /**
         * Freezes the {@link TargetSource} so that the next access to {@link #getTarget()} will get the results cached and
         * reused.
         */
        public void freeze() {
            this.frozen = true;
        }

        @NonNull
        public Class<?> getTargetClass() {
            return List.class;
        }

        public boolean isStatic() {
            return frozen;
        }

        @NonNull
        @SuppressWarnings({"rawtypes", "unchecked"})
        public synchronized Object getTarget() {

            Collection<Object> components = this.components == null //
                    ? getBeansOfTypeExcept(type, exclusions) //
                    : this.components;

            if (frozen && this.components == null) {
                this.components = components;
            }

            return new ArrayList(components);
        }

        public void releaseTarget(Object target) {
        }

        private Collection<Object> getBeansOfTypeExcept(Class<?> type, Collection<Class<?>> exceptions) {
            return Arrays.stream(context.getBeanNamesForType(type, false, eagerInit))
                    .filter(it -> !exceptions.contains(context.getType(it)))
                    .map(it -> context.getBean(it))
                    .collect(Collectors.toList());
        }
    }
}
