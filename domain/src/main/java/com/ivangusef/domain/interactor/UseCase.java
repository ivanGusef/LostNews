package com.ivangusef.domain.interactor;

import com.ivangusef.domain.executor.PostExecutionThread;
import com.ivangusef.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture). This interface represents a execution unit for different use cases (this
 * means any use case in the application should implement this contract).
 * <p/>
 * By convention each UseCase implementation will return the result using a {@link rx.Subscriber} that will execute its job in a background thread and
 * will post the result in the UI thread.
 */
public abstract class UseCase<T> {

    private final ThreadExecutor      threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();

    protected UseCase(final ThreadExecutor threadExecutor, final PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable<T> buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable()}.
     */
    public void execute(final Subscriber<T> useCaseSubscriber) {
        this.subscription = buildUseCaseObservable().subscribeOn(Schedulers.from(threadExecutor))
                                                    .observeOn(postExecutionThread.getScheduler())
                                                    .subscribe(useCaseSubscriber);
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
