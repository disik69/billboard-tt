<#macro mainLayout title>
    <html>
        <head>
            <meta charset="utf-8">
            <title>${title}</title>
            <meta name="description" content="">
            <meta name="viewport" content="width=device-width">

            <link rel="stylesheet" href="/bower_components/bootstrap/dist/css/bootstrap.css">

            <script src="/bower_components/jquery/dist/jquery.js"></script>
            <script src="/bower_components/bootstrap/dist/js/bootstrap.js"></script>
        </head>
        <body>
            <#nested>
        </body>
    </html>
</#macro>