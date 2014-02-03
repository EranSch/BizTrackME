        <jsp:include page="includes/header.html" />
        <title>Edit Customer</title>
    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation">
            <a href="/BizTrackME/view"><h1 class="navbar-text"><img src="/BizTrackME/images/BizTrackME_Logo.png" alt="BizTrackME"></h1></a>
        </nav>
            
        <div class="container">         
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <h1>Add Customer</h1>
                    <form method="post" role="form">
                        <input class="form-control" type="text" placeholder="First Name" name="firstName" required autofocus>
                        <input class="form-control" type="text" placeholder="Last Name"  name="lastName" required>
                        <input class="form-control" type="text" placeholder="Address"  name="address" required>
                        <input class="form-control" type="text" placeholder="Phone"  name="phone" >
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Add Customer</button>
                    </form>
                </div>
            </div>           
        </div>        
            <script src="//code.jquery.com/jquery.min.js"></script>
            <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    </body>
</html>
