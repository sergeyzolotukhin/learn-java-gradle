package ua.in.sz.contex.resolver;


public interface IntervalResolver<C extends IntervalResolverContext, R> {

	R resolve(C context);
}
