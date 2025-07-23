package ua.in.sz.hibernate.interceptor.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;

@Slf4j
public class TransactionInterceptor implements Interceptor {

    @Override
    public void afterTransactionBegin(Transaction tx) {
        log.debug("begin");
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
//        log.info("beforeTransactionCompletion");
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        log.debug("completion " + tx.getStatus());
    }
}
