use run.sh for localhost testing, puts this instance on 8082

You can test its "customers" endpoint with 

curl localhost:8082/api/customer

You can test its "orders" endpoint with curl like so

curl -X POST -d "customers=C100" -H "Content-Type: application/x-www-form-urlencoded" http://localhost:8082/api/customer/orders

if microspringboot3 is up, you will get
Orders for Customers O4321,O5432,O6543 is customers=C100

And if microserviceboot3 is NOT up, you will get

Orders for Customers F997,F998,F999 is customers=C100