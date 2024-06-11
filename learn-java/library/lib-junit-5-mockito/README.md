https://site.mockito.org/

https://chao2zhang.medium.com/unraveling-mockks-black-magic-e725c61ed9dd

-Dnet.bytebuddy.dump=D:/projects-java/_learn_framework/patterns/docs

https://stackoverflow.com/questions/14440270/how-does-mockito-when-invocation-work

=======================================================================================================================
ClassCreatingMockMaker -> MockMaker
createMockType

InlineDelegateByteBuddyMockMaker
InlineDelegateByteBuddyMockMaker -> implements ClassCreatingMockMaker, InlineMockMaker, Instantiator
=======================================================================================================================

TypeCachingBytecodeGenerator -> BytecodeGenerator
SubclassBytecodeGenerator -> 

=======================================================================================================================
MockMethodInterceptor 
    -> InvocationNotifierHandler<T> implements MockHandler
        -> NullResultGuardian<T> implements MockHandler
            -> MockHandlerImpl<T> implements MockHandler<T>
=======================================================================================================================
MockHandlerImpl
    - MatchersBinder
    - InvocationContainerImpl
=======================================================================================================================
InterceptedInvocation -> Invocation
=======================================================================================================================
InvocationContainerImpl
    DoAnswerStyleStubbing
    DefaultRegisteredInvocations
    LinkedList<StubbedInvocationMatcher> stubbed

DoAnswerStyleStubbing
    List<Answer<?>> answers

ThreadLocal<MockingProgress> MOCKING_PROGRESS_PROVIDER

MockingProgressImpl
    ArgumentMatcherStorage argumentMatcherStorage = new ArgumentMatcherStorageImpl()
        Stack<LocalizedMatcher> matcherStack

ArgumentMatcher

=======================================================================================================================
List mockedList = Mockito.mock(List.class);
1. generate class at runtime
2. create instance of class
3. set MockMethodInterceptor in mock. each mock has own instance of mock interceptor

Mockito.when(mockedList.get(0))
* create invocation matcher
* store at invocation container for this mock
? 

thenReturn("first")
