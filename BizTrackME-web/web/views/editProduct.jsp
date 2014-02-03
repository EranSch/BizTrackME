        <jsp:include page="includes/header.html" />
        <title>Edit Customer</title>
    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation">
            <h1 class="navbar-text">
                <a href="/view"><img src="/images/BizTrackME_Logo.png" alt="BizTrackME"></a>
            </h1>
        </nav>
            
        <div class="container">         
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <h1>Edit Product</h1>
                    <form method="post" role="update">
                        <input class="form-control" placeholder="Product Name" type="text" name="editProductName" value="<%= request.getAttribute("productName")%>" required autofocus>
                        <input class="form-control" placeholder="SKU"  type="text" name="editSku" value="<%= request.getAttribute("sku")%>" required>
                        <input class="form-control" placeholder="Price"  type="text" name="editPrice" value="<%= request.getAttribute("price")%>" required>
                        <input class="form-control" placeholder="Color"  type="text" name="editColor" value="<%= request.getAttribute("color")%>" required>
                        <input type="hidden" name="id" value="<%= request.getAttribute("id")%>">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Save Product</button>
                    </form>
                </div>
            </div>           
        </div>  
            
        <script src="//code.jquery.com/jquery.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    </body>
</html>
