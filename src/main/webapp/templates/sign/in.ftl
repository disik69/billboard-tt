<#include "../layouts/main.ftl">

<#assign title="Signin">

<@mainLayout title=title>
    <#if RequestParameters.error??>
        <div class="row">
            <div class="col-xs-offset-4 col-xs-4">
                <div class="alert alert-danger alert-dismissible fade in" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    Invalid email or password
                </div>
            </div>
        </div>
    </#if>
    <div class="row">
        <form novalidate class="col-xs-offset-4 col-xs-4" method="POST" action="/sign/in">
            <div class="form-group">
                <input type="text" name="username" class="form-control" placeholder="email">
            </div>
            <div class="form-group">
                <input type="password" name="password" class="form-control" placeholder="password">
            </div>
            <div class="form-group">
                <button type="submit" class="form-control btn btn-primary">OK</button>
            </div>
        </form>
    </div>
</@mainLayout>