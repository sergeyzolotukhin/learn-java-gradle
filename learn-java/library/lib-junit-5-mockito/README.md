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
1. generate class at runtime
2. create instance of class
3. set MockMethodInterceptor in mock
4. Prepare response for mock via MockHandlerImpl