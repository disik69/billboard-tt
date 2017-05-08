<#include "../layouts/main.ftl">

<#assign title="Update publication">
<#assign style>
    <link rel="stylesheet" href="/styles/publication/update.css">
</#assign>

<#assign bindingResult=org\.springframework\.validation\.BindingResult\.publication>
<#if (! publication.id??) && (storedPublication??)>
    <#assign publication=storedPublication>
</#if>

<@mainLayout title=title style=style>
    <div class="row">
        <form class="form-horizontal col-xs-offset-3 col-xs-6 update-form" action="/publication/update/${publication.id}" method="POST">
            <div class="row">
                <div class="col-xs-6 title">${publication.title}</div>
                <div class="col-xs-3">${publication.topic}</div>
                <div class="col-xs-3">${publication.formatCreatedAt("dd-MM-yy HH:mm:ss")}</div>
            </div>
            <div class="row <#if bindingResult.hasFieldErrors("body")>has-error</#if>">
                <div class="col-xs-12">
                    <textarea class="form-control" name="body" rows="8" id="body">${publication.body}</textarea>
                    <#if bindingResult.hasFieldErrors("body")>
                        <span class="help-block">${bindingResult.getFieldError("body").getDefaultMessage()}</span>
                    </#if>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-3">
                    <button type="submit" class="btn btn-primary">Update</button>
                </div>
            </div>
        </form>
    </div>
</@mainLayout>