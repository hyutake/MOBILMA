## P.S I'm just gonna use this to communicate some of the more major changes

# Folder explainations: (MVC design pattern)
<ul>
    <li>Boundary: For user interfaces, to just show what the user will see on the console and get their input</li>
    <li>Constants: Mainly Enums</li>
    <li>Controller: For programs that 'translate' the user input acquired from UI into input that we will use in our program (so we can control this 'translation')</li>
    <li>Database: For the .dat / .txt files that store data</li>
    <li>Exception: For 'custom' exception declarations (Just so that any exception thrown give more specific information)</li>
    <li>Model: For entity classes, i.e. Classes that store some data that we will be using</li>
    <li>Service: For code to implement certain logics (usually where the main 'computing' part is), everything else is really just to get User input to the Service</li>
</ul>
^Try to implement your code based on this so that the combination later on is easier

## Pricing logic
<ul>
    <li>PriceList is the Model Object that we will read and write from to eventually calculate prices</li>
    <li>Essentially, PriceList contains values of base and holiday prices (flat value) and maps Enums (for ageGroups etc.) to their respective modifier values</li>
    <li>PriceController gets the factors that affect prices (e.g. Age group etc.) and initialises an ArrayList of PriceModifiers</li>
    <ul>
        <li>PriceModifier(s) are Objects that contain a base price and a multiplier (modifer) value, these are read from PriceList</li>
    </ul>
    <li>PriceCalculator provides the function to calculate the price of 1 booked ticket</li>
</ul>
<b>TLDR: PriceController is where the logic starts - I need it to 'receive' these inputs @AtharvGup:</b>
<ul>
    <li>AgeGroup (Enum) - need to 'ask' in BookingUI</li>
    <li>CinemaClass (Enum) - need to 'ask' in BookingUI</li>
    <li>MovieType (Enum) - acquired from the chosen Movie, 'asked' in BookingUI</li>
    <li>IsHoliday (Boolean) - need to check booked date against Holidays/Weekend @TheGreatReee</li>
</ul>
PriceController will 'receive' these via its constructor (See Service -> AdminPriceService.java -> testTicketPrice() method for an example)

## FileAccessController
<ul>
    <li>Changed it to an Interface - cannot be instantiated, BUT can still work as per how it used to</li>
    <li>Read from Database is now BY DEFAULT, there is no alternative offered (so if you run the code anywhere else but from SC2002 dir, it will probably fail)</li>
    <li>Classes that IMPLEMENT from FileAccessController are expected to do file reading and writing for ONE .dat file ONLY (makes it easier to extend later on for the Customer and Seating layout databases)</li>
    <li>MobilmaAdminApp is untouched, but it does NOT work with this new way of enabling file access (read/write). I have rewrote it as AdminUI in the Boundary folder (yes, AdminUI used to contain my old code for the Admin portion of editing Movie data)</li>
    <li>The old code in AdminUI is now moved (and also updated accordingly) to Services -> Admin -> AdminMovieService</li>
</ul>
<b>TLDR</b>: A bunch of stuff changed, but none of it (should) affect the working code. To run adminUI, just use: AdminUI adminUI = new AdminUI();

## Login functionality
<ul>
    <li>According to the review lecture on 1/11/22, the prof would prefer that the login functionality be 'combined', i.e. Admin and regular movie goers use the SAME user interface to login</li>
    <li>User is the class used to store information of a user - differentiated by their UserType (Enum) - contains username, password, email, mobileNumber and userType</li>
    <li>Login works by getting the user input for their username and password, and then doing exact matching with all the users in the database</li>
    <ul>
        <li>User Database information is always sorted such that Admin user information is always "on top", meaning that Admin user accounts are always checked first</li>
        <li>Normal user login info and Admin login info CANNOT be the same - this will be checked during account creation</li>
    </ul>
    <li>File generation for User data creates an empty .dat file before prompting for the creation of an Admin account (the current UserDatabase.dat file contains 1 Admin account: username = mobilmaAdmin, password = Mobilma2002!</li>
    <li>Successful login to admin account -> new AdminUI() is called</li>
    <li>Successful login to regular user account -> UserUI.UserApp() is called</li>
    <li>Only Admin accounts can create other admin accounts (added a new option into AdminUI for that)</li>
    <li>Not 100% sure if it will work, but can try having the Customer class inherit from the User class</li>
</ul>

## Booking functionality
<ul>
    <li>User class acts as the "old" Customer class - includes both Admins and regular Customers (which is fine because the login logic ensures that Admins will never enter UserUI (in theory)</li>
    <li>User class stores a List of BookingInfo, where booking information for the customer is stored</li>
    <li>Users are stored in the UserDatabase.dat file</li>
    <li>Cineplex contains a List of Cinemas, and CineplexLocation (Enum)</li>
    <ul>
        <li>Initialisation works like this (for now), Cineplex receives a CineplexLocation enum and will read from 1 of 3 Cineplex files based on the location</li>
        <li>Cineplex files like CineplexPunggol.dat store a List of Cinemas which is basically the corresponding 3 cinema halls for each cineplex</li>
    </ul>
    <li>Cinema mainly contains the Cinema code and the Cinema seating layout</li>
    <ul>
        <li>Seating layout is just a simple int[][] array for now, tbh I think can leave it alone but you can try to integrate your part if you want to @sleepreap</li>
        <li>Cinema code is just a 3 letter identifier for the cinema</li>
    </ul>
    <li>Upon close inspection, one might realise that the User's booking info storage isn't directly synced with the Cinema's layout - unless they are both newly generated - gonna leave it that way I think, there's just no time</li>
</ul>
