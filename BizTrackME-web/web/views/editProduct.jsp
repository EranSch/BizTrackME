        <jsp:include page="includes/header.html" />
        <title>Edit Customer</title>
    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation">
            <h1 class="navbar-text"><img src="/images/BizTrackME_Logo.png" alt="BizTrackME"></h1>
        </nav>
            
        <div class="container">
            <h1>Edit Product</h1>
            
            <ul>
                <li>Product Name: <%= request.getAttribute("productName") %></li>
                <li>SKU: <%= request.getAttribute("sku") %></li>
                <li>Price: <%= request.getAttribute("price") %></li>
                <li>Color: <%= request.getAttribute("color") %></li>
            </ul>
        </div>
            
            <script src="//code.jquery.com/jquery.min.js"></script>
            <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    </body>
</html>
