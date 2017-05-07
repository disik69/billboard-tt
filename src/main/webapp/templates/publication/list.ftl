<#import "/macroses/common.ftl" as common>

<#include "../layouts/main.ftl">

<#assign title="Publications">
<#assign style>
    <link rel="stylesheet" href="/styles/publication/list.css">
</#assign>

<@mainLayout title=title style=style>
    <div class="row">
        <form class="form-inline col-xs-offset-3 col-xs-6 list-filter-form" action="/publication/list" method="GET">
            <div class="form-group">
                <label>User</label>
                <input type="text" class="form-control" name="user"
                    <#if RequestParameters.user??>value="${RequestParameters.user}"</#if>>
            </div>
            <div class="form-group">
                <label>Topic</label>
                <select class="form-control" name="topic">
                    <option value="">ALL</option>
                    <#list topics as topic>
                        <option value="${topic}"
                            <#if RequestParameters.topic?? && RequestParameters.topic == topic>selected="selected"</#if>>
                            ${topic}
                        </option>
                    </#list>
                </select>
            </div>
            <@springSecurity.authorize access="hasRole('ROLE_USER')">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="onlyMine" value="true"
                            <#if RequestParameters.onlyMine??>checked="checked"</#if>>&nbsp;Only mine
                    </label>
                </div>
            </@springSecurity.authorize>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form>
    </div>
    <#list publications as publication>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6 list-publication">
                <div class="row">
                    <div class="col-xs-6 title">
                        <#assign ownContent>
                            <a href="/publication/update/${publication.id}">${publication.title}</a>
                        </#assign>
                        <#assign commonContent>
                            ${publication.title}
                        </#assign>
                        <@common.sourceOwner user=publication.user ownContent=ownContent commonContent=commonContent/>
                    </div>
                    <div class="col-xs-3">${publication.topic}</div>
                    <div class="col-xs-3">${publication.formatCreatedAt("dd-MM-yy H:m:s")}</div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        ${publication.body}
                    </div>
                </div>
                <div class="row">
                    <#assign ownContent>
                        <form class="col-xs-3" method="POST" action="/publication/delete/${publication.id}">
                            <button class="btn btn-danger" type="submit">Delete</button>
                        </form>
                    </#assign>
                    <#assign commonContent>
                        <div class="col-xs-3"></div>
                    </#assign>
                    <@common.sourceOwner user=publication.user ownContent=ownContent commonContent=commonContent/>
                    <div class="col-xs-offset-6 col-xs-3">${publication.user.name}</div>
                </div>
            </div>
        </div>
    </#list>
    <#if isPaginationEnabled>
        <nav class="row">
            <div class="col-xs-offset-3 col-xs-6 list-pagination">
                <ul class="pagination">
                    <#if isLeftEnabled>
                        <li>
                            <a href="/publication/list?page=${currentPage - 1}&rows=${rowCount}${common.addSearchParams()}">
                                <span>&laquo;</span>
                            </a>
                        </li>
                    <#else>
                        <li class="disabled">
                            <span>&laquo;</span>
                        </li>
                    </#if>
                    <#list pageBlock as blockPage>
                        <li class="<#if currentPage == blockPage>active</#if>">
                            <a href="/publication/list?page=${blockPage}&rows=${rowCount}${common.addSearchParams()}">${blockPage}</a>
                        </li>
                    </#list>
                    <#if isRightEnabled>
                        <li>
                            <a href="/publication/list?page=${currentPage + 1}&rows=${rowCount}${common.addSearchParams()}">
                                <span>&raquo;</span>
                            </a>
                        </li>
                    <#else>
                        <li class="disabled">
                            <span>&raquo;</span>
                        </li>
                    </#if>
                </ul>
            </div>
        </nav>
    </#if>
</@mainLayout>