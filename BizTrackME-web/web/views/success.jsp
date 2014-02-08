<jsp:include page="includes/header.html" />
        <title>BizTrackME</title>
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-default col-md-6 col-md-offset-3" role="navigation">
                <h1 class="navbar-text">
                    <img src="/BizTrackME/images/BizTrackME_Logo.png" alt="BizTrackME" class="centered">
                </h1>
            </nav>

            <div class="row">                
                <div class="col-md-6 col-md-offset-3">
                    <h1>Your order has been submitted!</h1>
                    <p>Thank you for your order! Please send a check for the amount of:</p>
                    <div class="well well-lg" style="font-weight:bold;font-size:35px;text-align:center"><%= request.getAttribute("totalCost")%></div>
                </div>
            </div>

            <script src="//code.jquery.com/jquery.min.js"></script>
            <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        </div>
    </body>
</html>
