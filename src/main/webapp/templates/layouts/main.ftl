<#assign springSecurity=JspTaglibs["http://www.springframework.org/security/tags"]>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#assign springForm=JspTaglibs["http://www.springframework.org/tags/form"]>

<#macro mainLayout title>
    <html>
        <head>
            <meta charset="utf-8">
            <title>${title}</title>
            <meta name="description" content="">
            <meta name="viewport" content="width=device-width">

            <link rel="stylesheet" href="/bower_components/bootstrap/dist/css/bootstrap.css">
            <link rel="stylesheet" href="/styles/main.css">

            <script src="/bower_components/jquery/dist/jquery.js"></script>
            <script src="/bower_components/bootstrap/dist/js/bootstrap.js"></script>
        </head>
        <body>
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a href="/" class="navbar-brand">Billboard</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="main-navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" role="button">
                                    Publication<span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="/publication/list">List</a></li>
                                    <li><a href="/publication/create">New</a></li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" role="button">
                                    <@springSecurity.authorize access="hasRole('ROLE_USER')">
                                        <span class="glyphicon glyphicon-user"></span>&nbsp;<@springSecurity.authentication property="principal.name"/>
                                    </@springSecurity.authorize>
                                    <@springSecurity.authorize access="isAnonymous()">
                                        Sign
                                    </@springSecurity.authorize>
                                </a>
                                <ul class="dropdown-menu">
                                    <@springSecurity.authorize access="isAnonymous()">
                                        <li><a href="/sign/in">Signin</a></li>
                                        <li><a href="/sign/up">Signup</a></li>
                                    </@springSecurity.authorize>
                                    <@springSecurity.authorize access="hasRole('ROLE_USER')">
                                        <li><a href="/sign/out">Signout</a></li>
                                    </@springSecurity.authorize>
                                </ul>
                            </li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>

            <div class="container">
                <#nested>
            </div>

            <footer>
                <p><b>Billboard&COPY;</b> 2017</p>
            </footer>
        </body>
    </html>
</#macro>