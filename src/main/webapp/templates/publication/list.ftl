<#include "../layouts/main.ftl">

<#assign title="Publications">
<#assign style>
    <link rel="stylesheet" href="/styles/publication/list.css">
</#assign>

<@mainLayout title=title style=style>
    <div class="row">
        <form class="form-inline col-xs-offset-3 col-xs-6 list-filter-form">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="User">
            </div>
            <div class="form-group">
                <select class="form-control">
                    <option value="">Topic</option>
                    <option value="">2</option>
                    <option value="">3</option>
                    <option value="">4</option>
                    <option value="">5</option>
                </select>
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox">&nbsp;Only mine
                </label>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form>
    </div>
    <#list publications as publication>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6 list-publication">
                <div class="row">
                    <div class="col-xs-6 title"><a href="#">${publication.title}</a></div>
                    <div class="col-xs-3">${publication.topic}</div>
                    <div class="col-xs-3">${publication.createdAt}</div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        ${publication.body}
                    </div>
                </div>
                <div class="row">
                    <form class="col-xs-3">
                        <button class="btn btn-danger" type="submit">Delete</button>
                    </form>
                    <div class="col-xs-offset-6 col-xs-3">${publication.userId}</div>
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
                            <a href="/publication/list?page=${currentPage - 1}&rows=${rowCount}">
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
                            <a href="/publication/list?page=${blockPage}&rows=${rowCount}">${blockPage}</a>
                        </li>
                    </#list>
                    <#if isRightEnabled>
                        <li>
                            <a href="/publication/list?page=${currentPage + 1}&rows=${rowCount}">
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