# Introduction 

The "DVD Center" (referred to as store) is an on-line service that offers DVD 
movie rentals to its customers.  These rentals are requested on-line and then 
mailed to the customer when available.  The customer returns video rentals by 
mail to the store and may subsequently request additional movies.  The following
are the assumptions for the simulation:

   * This simulation begins on January 1, 2008. 
   * This simulation requires no user interface; the output is a log file 
   * This simulation requires no persistent storage (database)
   
## Customers

Customers can rent up to two videos (standard or blu-ray) at a time.  If a 
customer exceeds this rental limit, a "customer exceeded" notice should be 
displayed and the requested movie queued for delivery until the customer 
returns a video. Likewise, if a video is out of stock (see inventory below), a 
"video not in stock" notice should be displayed and the request should be 
queued for delivery when another customer returns a copy.  

The term "queued for delivery" simply means that a customer request will be 
stored and processed as soon as the video is available.  

## Payment

Payment is not a part of this simulation: you can assume that customers already pay a 
monthly subscription fee and pay no additional fee to rent a video. 

## Movies

Movie rentals can be either standard or Blu-Ray type DVDs. The DVD Center store is limited 
to two copies of each video. 

The store mails DVDs to customers and customers mail them back.  Mailing DVDs takes one 
day to mail a DVD.  So, if a customer mails back a video on January 2, it will be returned to 
movie stock the following day (January 3). 

# License

faux-flix is licensed under the BSD license.

# Requirements

faux-flix requires a Java Runtime Environment (JRE) version 1.5 or higher.  While it has only 
been tested under Mac OS X 10.5, it should function properly under Linux and Windows based
operating systems.

# Operation

The simulator is executed as follows:

	java -ea -jar faux-flix-1.0.0 [data set directory]

The data set directory must contain the following files:

   * movies.csv: Defines the movie catalog for the simulation with a
		single column containing the movie title.
   * customers.csv: Defines the customers and their membership levels
		for the simulation with the following columns:
      - Customer Name
      - Membership Level (standard or executive)
   * inventory.csv: Defines the store's stock configuration for the simulation 
		with the following columns:
      - Movie Title (must be present in movies.csv)
      - Media Type (dvd or bluray)
	  - Quantity
   * commands.csv: Defines the commands that will be executed by the simulation
		with the following columns:
      - Date occurred (in the form of MM/dd/yyyy)
      - Customer Name (must be present in customers.csv)
      - Command Type (requests or mails)
      - Movie Title (must be present in movies.csv)
      - Media Type (dvd or bluray)

The following datasets are provided with the distribution in the data directory:

   * default-dataset: All customers are standard level members
   * executive-dataset: All customers are standard level members except Joe 
		and John are executive level members.