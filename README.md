# JPA issue

I want to use one model and two persistence units so I can store data from that one
model in different tables (or even databases).

I'm experiencing weird behaviour and would like some help or explantion why this happens.

## Requirements

- maven
- docker
- docker-compose

## Install

	docker-compose up -d
	./rebuild.sh

## Explanation

I have one JPA entities, Foo,  and a POJO Bar. 

Foo has attributes

- String id1 (@Id)
- String id2 (@Transient)
- String test1
- String test2 (@Transient)
- Bar bar (@Transient)

Bar has attributes

- String bartest1
- String bartest2

I have two services which have their own persistence.xml but share the Foo and Bar entities.
Service 1 does not have an orm.xml so it uses the JPA annotations in Foo and Bar.

This will generate a table

- my_foo_1

with columns

- id1 (PK) 
- test 

Service2 overrides these annotations with an orm.xml which specifies <xml-mapping-metadata-complete/>
so it should completely ignore the annotations.

This will generate a table

- my_foo_2

with columns

- id2 (PK)
- id1
- test1
- test2
- bartest1
- bartest2

So far so good.

I created a rest resource so I can do http call and insert some data.
The logging properties are set to FINEST so we see in the log what sql statements are executed.

If I call 

	curl -X POST -H "Content-type: application/json" -H "Accept: application/json" -s "http://localhost:48080/test-jpa-issue/rest/test/1/hello-world"

then this nicely insert a record in table my_foo_1.

If I call
	
	curl -X POST -H "Content-type: application/json" -H "Accept: application/json" -s "http://localhost:48080/test-jpa-issue/rest/test/2/hello-world"

then I see the following in the logs and I also get an error

	INSERT INTO my_foo_2 (ID2, ID1, test_1, test_2, bar_test_1, bar_test_2) VALUES (?, ?, ?, ?, ?, ?)
	bind => [null, a2ef3a1e-94fc-4b5f-9c40-6334c44a590a, hello-world 1 is converted, null, null, null]

What I see here is that the ID2 primary key is not set. While debugging I can see that it does have a value when I'm calling em.persist().
Also the the columns test1, bartest1 and bartest2 are null while they are set.

So it doesn't work so maybe I made a mistake in the orm.xml or somewhere else?

Now go to the service1 module and rename persistence.xml to persistence.xml.old. 
Also comment out @PersistenceUnit(..) in Service.java.

Effectively I'm now disabling the persistence unit in service1.

Now do

    ./rebuild.sh

And run the second curl command again. It works! I get

    INSERT INTO my_foo_2 (ID2, ID1, test_1, test_2, bar_test_1, bar_test_2) VALUES (?, ?, ?, ?, ?, ?)
    bind => [dd9fabc6-a8c9-4363-b2c4-23179ccbf9ba, dbd36cfd-a25c-4a84-9b67-9ba0bbf4f4fe, hello-world 1 is converted, hello-world 2, bar test 1, bar test 2]|#]

## Conclusion?

So the conclusions seems that the two persistence units are conflicting? 
I would expect that a persistence unit is standalone and is not affected by other persistence units.

Can anyone tell me what is going on here.
The JPA2.2 spec states that one object can be used in multiple persistence contexts.
It also states I should not use 2 orm.xml files to change the definition for one class.
It also states that orm.xml is an override for annotations on the class.

So why is this not working.
