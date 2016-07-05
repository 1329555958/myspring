namespace java demo
exception SerializedException
{
    1: required string payload
}


service  HelloWorldService {
  string sayHello(1:string username) throws (1:SerializedException servializedException)
}