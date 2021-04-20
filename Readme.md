#QA Project
API Proyecto para la implementacion y control de conocimientos 
de testing para el coverage de una API de reserva de hoteles y vuelos.

Se consolido un % de coverage mayor al 80%. 
##Casos de uso implementados
    
- US0001
- US0002
- US0003
- US0004
- US0005
- US0006

##Endpoints

#### US0001-Lista de todos los hoteles : 
localhost:8080/api/v1/hotels
#### US0002-Lista de hotel por flitros :
localhost:8080/api/v1/hotels?dateFrom=09/02/2021&dateTo=21/03/2021&destination=Buenos Aires
#### US0003-Reserva de hotel :
localhost:8080/api/v1/booking
######
Body utilizado para la peticion de reserva de un hotel
```json 
{
    "userName" : "seba_gonzalez@unmail.com",
    "booking" : {
        "dateFrom" : "10/02/2021",
        "dateTo" : "20/03/2021",
        "destination" : "Puerto Iguazú",
        "hotelCode" : "CH-0002",
        "peopleAmount" : 2,
        "roomType" : "Doble",
        "people" : [
            {
                "dni" : "12345678",
                "name" : "Pepito",
                "lastName" : "Gomez",
                "birthDate" : "10/11/1982",
                "mail" : "pepitogomez@gmail.com"
            },
             {
                "dni" : "13345678",
                "name" : "Fulanito",
                "lastName" : "Gomez",
                "birthDate" : "10/11/1983",
                "mail" : "fulanitogomez@gmail.com"
            }
        ],
        "paymentMethod" : {
            "type" : "CREDIT",
            "number" : "1234-1234-1234-1234",
            "dues" : 2
        }
    }
}
```

#### US0004-Listado de vuelos disponibles :
localhost:8080/api/v1/flights

#### US0005-Listado de vuelos por flitros :
localhost:8080/api/v1/flights?origin=Cartagena&destination=Medellín&dateFrom=22/01/2021&dateTo=01/02/2021

#### US0006-Reserva de vuelo :
#####
Body utilizado para la peticion de reserva de un vuelo
```json 
{
    "userName" : "seba_gonzalez@unmail.com",
    "flightReservation" : {
        "dateFrom" : "10/02/2021",
        "dateTo" : "15/02/2021",
        "origin" : "Buenos Aires",
        "destination" : "Puerto Iguazú",
        "flightNumber" : "BAPI-1235",
        "seats" : 2,
        "seatType" : "Economy",
        "people" : [
            {
                "dni" : "12345678",
                "name" : "Pepito",
                "lastName" : "Gomez",
                "birthDate" : "10/11/1982",
                "mail" : "pepitogomez@gmail.com"
            },
             {
                "dni" : "13345678",
                "name" : "Fulanito",
                "lastName" : "Gomez",
                "birthDate" : "10/11/1983",
                "mail" : "fulanitogomez@gmail.com"
            }
        ],
        "paymentMethod" : {
            "type" : "CREDIT",
            "number" : "1234-1234-1234-1234",
            "dues" : 6
        }
    }
}
```