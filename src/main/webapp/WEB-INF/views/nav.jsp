
    
    <!-- Navigation -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark" role="navigation">
        <div class="container">
            <a class="navbar-brand" href="/">JBoss Store</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
              <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                  <a class="nav-link" href="./home-direct.page">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="./viewCart.page" id="viewCartLink">View Cart</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link " href="#" >View Past Orders</a>
                </li>              
                <li class="nav-item">
                  <a class="nav-link " href="#" > login as : ${sessionScope.SESS_USER}</a>
                </li>
              </ul>
              <form class="form-inline mt-2 mt-md-0" id="searchForm">
                <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
              </form>
            </div>
          </nav>
        </div>
        <!-- /.container -->
    </nav>
