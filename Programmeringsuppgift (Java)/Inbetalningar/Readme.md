# Hello

My name is Dimosthenis Emmanouil and iam a fullstack developer. I have created a project given by me from Lumera to solve a problem.
I have created a NodeJS code aswell and redid it with Java. This Java project dosent have a api that is served locally but it will functions as it do.

I have used Visual Studio Code to develop this project. To start the programm you need to navigate to MyMain.java file which is inside the foler ./se/dedev/filetools/MyMain.java and then press the Start button top right corner.

inside se/dedev you can find my soulution for the problem. MyMain.java file is the main handler that the purpose is to send the files from ./uploads folder to a respective controller.

MyMain will call upon my handler which then will decide that controller (class) will decode the file and then return the data into the console and the Treatment class.

The Treatment class is not finished but can de futher developed to send the information to a database of choice.

se/devdev/filetools/se/dedev/Pojo
The se.dedev.filetools.se.dedev.Pojo package contains a set of Java classes that define data structures and operations related to payment services. Here's a description of the classes inside the package:

**BetalningsServiceData:** This class represents payment service data and contains information such as account number, currency, payment date, and a list of payments. It provides methods to set and retrieve the data, as well as read and print the opening post information.

**BetalningsServicePayment:** This class represents an individual payment within the payment service. It includes properties like the payment amount and a reference. It provides methods to set and retrieve these properties, as well as read and print the payment information.

**InBetalningsTjanstenData:** This class is similar to BetalningsServiceData but represents data specific to a different payment service. It also includes properties like account number, currency, payment date, and a list of payments. Additionally, it has a property called slutpost, and methods to set and retrieve the data, as well as read and print the opening and closing post information.

**InBetalningsTjanstenPayment:** This class is similar to BetalningsServicePayment but represents a payment specific to the InBetalningsTjanstenData payment service. It includes properties like the payment amount and a reference. It provides methods to set and retrieve these properties, as well as read and print the payment information.
