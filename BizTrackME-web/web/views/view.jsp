        <jsp:include page="includes/header.html" />
        <title>BizTrackME</title>
    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation">
            <h1 class="navbar-text"><img src="images/BizTrackME_Logo.png" alt="BizTrackME"></h1>
        </nav>
            
        <div class="row">
            <div class="col-md-6"><%= request.getAttribute("custTable") %></div>
            <div class="col-md-6"><%= request.getAttribute("prodTable") %></div>
        </div>
            
            <script src="//code.jquery.com/jquery.min.js"></script>
            <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
            <script src="/vendor/jquery.filtertable.min.js"></script>
            <script type="text/javascript">
                $('table').filterTable();
            </script>            
    </body>
</html>
