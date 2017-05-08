<#include "../layouts/main.ftl">

<#assign title="Create publication">
<#assign style>
    <link rel="stylesheet" href="/styles/publication/create.css">
</#assign>

<#assign bindingResult=org\.springframework\.validation\.BindingResult\.publication>

<@mainLayout title=title style=style>
    <div class="row">
        <form class="form-horizontal col-xs-offset-3 col-xs-6 create-form" action="/publication/create" method="POST">
            <div class="form-group <#if bindingResult.hasFieldErrors("title")>has-error</#if>">
                <label for="title" class="col-xs-2 control-label">Title</label>
                <div class="col-xs-10">
                    <input type="text" name="title" class="form-control" id="title" value="${publication.title}">
                    <#if bindingResult.hasFieldErrors("title")>
                        <span class="help-block">${bindingResult.getFieldError("title").getDefaultMessage()}</span>
                    </#if>
                </div>
            </div>
            <div class="form-group">
                <label for="topic" class="col-xs-2 control-label">Topic</label>
                <div class="col-xs-10">
                    <select class="form-control" name="topic">
                        <#list topics as topic>
                            <option value="${topic}"
                                <#if publication.topic?? && publication.topic == topic>selected="selected"</#if>>
                                ${topic}
                            </option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group <#if bindingResult.hasFieldErrors("body")>has-error</#if>">
                <label for="body" class="col-xs-2 control-label">Publication</label>
                <div class="col-xs-10">
                    <textarea class="form-control" name="body" rows="8" id="body">${publication.body}</textarea>
                    <#if bindingResult.hasFieldErrors("body")>
                        <span class="help-block">${bindingResult.getFieldError("body").getDefaultMessage()}</span>
                    </#if>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <button type="submit" class="btn btn-primary">Create</button>
                </div>
            </div>
        </form>
    </div>
</@mainLayout>