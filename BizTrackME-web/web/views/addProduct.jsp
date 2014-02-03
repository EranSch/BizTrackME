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
                    <h1>Add Product</h1>
                    <form method="post" role="form">
                        <input class="form-control" type="text" placeholder="Product Name" name="productName" required autofocus>
                        <input class="form-control" type="text" placeholder="SKU"  name="sku" required>
                        <input class="form-control" type="number" placeholder="Price"  name="price" required>
                        <input class="form-control" type="text" placeholder="Color"  name="color" >
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Add Product</button>
                    </form>
                </div>
            </div>           
        </div>        
            <script src="//code.jquery.com/jquery.min.js"></script>
            <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    </body>
</html>
