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
    <div class="row">
        <div class="col-xs-offset-3 col-xs-6 list-publication">
            <div class="row">
                <div class="col-xs-6 title"><a href="#">Title</a></div>
                <div class="col-xs-3">Topic</div>
                <div class="col-xs-3">Date</div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                </div>
            </div>
            <div class="row">
                <form class="col-xs-3">
                    <button class="btn btn-danger" type="submit">Delete</button>
                </form>
                <div class="col-xs-offset-6 col-xs-3">User</div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-offset-3 col-xs-6 list-publication">
            <div class="row">
                <div class="col-xs-6 title"><a href="#">Title</a></div>
                <div class="col-xs-3">Topic</div>
                <div class="col-xs-3">Date</div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                    Body
                </div>
            </div>
            <div class="row">
                <form class="col-xs-3">
                    <button class="btn btn-danger" type="submit">Delete</button>
                </form>
                <div class="col-xs-offset-6 col-xs-3">User</div>
            </div>
        </div>
    </div>
    <nav class="row">
        <div class="col-xs-offset-3 col-xs-6 list-pagination">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</@mainLayout>