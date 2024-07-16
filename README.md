# Out of the box:
- run docker desktop
- 



# Errors:

`Get http://%2F%2F.%2Fpipe%2Fdocker_engine/v1.25/containers/json: open
//./pipe/docker_engine: The system cannot find the file specified.
`

This was fixed by opening docker desktop prior to running
`docker compose up -d`

# Connect Pgadmin
- to connect pgadmin, you have to point it to the container_name defined in docker-compose.yml
- you will need the folowing databases (in production they are all hosted on different servers with different ports)
- order
- product
- payment

# Mongo running locally
- I had an instance of mognodb running locally that was not setup with a user password on init. 
So the customer service was failing to authenticate with it because it was sening it a user and password.
when I removed the user and password for mognodb it was able to connect.
this means that I need to blow away the locally running mongodb that is stealing traffic ont he same port on localhost
and then I should be able to revert the files I changed so that the correct mongodb is being talked to. 
- you will need a customer database with a customer collection

- once you have all of the above setup, you will have to run the microservices
- to front load products
- then you will need to send a post request for creating a new customer:
- to http://localhost:8222/api/v1/orders
- this the body
`{
"reference": "MS-20241201",
"amount":900,
"paymentMethod": "PAYPAL",
"customerId": "6696481983403616d5a26f8d",
"products":[
{
"productId":1,
"quantity":1
},
{
"productId":51,
"quantity":1
}
]
}`
then you should be able to run a get to products with
- http://localhost:8222/api/v1/products

and you should be able to create a new order and view it in pgadmin after sending the post:
- http://localhost:8222/api/v1/orders
- with the body:
- `{
  "reference": "MS-20241201",
  "amount":900,
  "paymentMethod": "PAYPAL",
  "customerId": "6696481983403616d5a26f8d",
  "products":[
  {
  "productId":1,
  "quantity":1
  },
  {
  "productId":51,
  "quantity":1
  }
  ]
  }`