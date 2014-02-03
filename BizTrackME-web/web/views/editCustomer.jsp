        <jsp:include page="includes/header.html" />
        <title>Edit Customer</title>
    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation">
            <a href="/view"><h1 class="navbar-text"><img src="/images/BizTrackME_Logo.png" alt="BizTrackME"></h1></a>
        </nav>
            
        <div class="container">         
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <h1>Edit Customer</h1>
                    <form method="post" role="update">
                        <input class="form-control" type="text" name="editFirstName" value="<%= request.getAttribute("firstName") %>" required autofocus>
                        <input class="form-control" type="text" name="editLastName" value="<%= request.getAttribute("lastName") %>" required>
                        <input class="form-control" type="text" name="editAddress" value="<%= request.getAttribute("address") %>" required>
                        <input class="form-control" type="text" name="editPhone" value="<%= request.getAttribute("phone") %>" required>
                        <input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Save Customer</button>
                    </form>
                </div>
            </div>           
        </div>        
            <script src="//code.jquery.com/jquery.min.js"></script>
            <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    </body>
</html>
