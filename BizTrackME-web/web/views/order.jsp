        <jsp:include page="includes/header.html" />
        <title>BizTrackME</title>
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-default col-md-6 col-md-offset-3" role="navigation">
                <h1 class="navbar-text">
                    <a href="/BizTrackME/order"><img src="/BizTrackME/images/BizTrackME_Logo.png" alt="BizTrackME" class="centered"></a>
                </h1>
            </nav>

            <div class="row">                
                <div class="col-md-6 col-md-offset-3">
                    <h1> Order Form</h1>
                    <form role="form" method="post">
                    <%= request.getAttribute("prodTable")%>
                    <button type="submit" class="btn btn-primary btn-block">Submit Order</button>
                    </form>
                </div>
            </div>

            <script src="//code.jquery.com/jquery.min.js"></script>
            <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
            <script src="/BizTrackME/vendor/jquery.filtertable.min.js"></script>
            <script type="text/javascript">
                $('table').filterTable();
            </script> 
        </div>
    </body>
</html>
