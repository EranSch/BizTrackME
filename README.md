![](https://github.com/Swingline0/BizTrackME/raw/master/BizTrackME-client/src/biztrackme/client/BizTrackME_Logo.png)

_**BizTrackME** - Business managment milleniums ahead of the curve_

##Description
Once again, FooCorp has decided to develop the next big thing in sales and customer management. Following in the footsteps of SalesTracker98, **BizTrackME** seeks to change the face of two discrete business realms, customer relations management and product inventory. Not only will **BizTrackME** offer a seamless user experience, it will also utilize a client/server architecture allowing for simultaneous use and unrestricted sharing of data between users. **BizTrackME** will be built on the established Java platform and will therefore be cross-platform out of the box, making it perfect for any business operating in the new millennium.

**BizTrackME** demonstrates a simplistic client-server architecture written in Java with each [tagged release](https://github.com/Swingline0/BizTrackME/releases) featuring a different implementation. 

- **IP1** - Serverside storage using single text files, Client is Java-Swing based desktop application
- **IP2** - Same as IP1 but with multi-threaded server component, allowing concurrent connections
- **IP3** - Server now uses MySQL instead of filesystem, Client featureset expanded
- _IP3 - (Future release) Client component replaced with web app_

##Notes
BizTrackME 3.0 now requires MySQL in order to operate. To get started, import the `database.sql` file found in this project. Configuration may need to be updated based on the environment.

##Usage
1. Start the server by executing the `RunServer.bat` script or executing the JAR directly.
2. Start client by executing the `RunClient.bat` script or executing the JAR directly.
3. Get to work!

##Screenshots

![Multiple clients accessing single server simultaneously](https://github.com/Swingline0/BizTrackME/raw/screenshots/mutli-image.png)

_Multiple clients accessing single server simultaneously_

![Tabular display of data, received from serverside database](https://github.com/Swingline0/BizTrackME/raw/screenshots/tables.png)

_Tabular display of data, received from serverside database_

![Server supports all typical CRUD operations](https://github.com/Swingline0/BizTrackME/raw/screenshots/server-logging.png)

_Server supports basic CRUD operations_
