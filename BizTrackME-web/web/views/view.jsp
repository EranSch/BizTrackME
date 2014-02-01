<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
        <title>BizTrackME</title>
<meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
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
    </body>
</html>
