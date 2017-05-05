<#include "../layouts/main.ftl">

<#assign title="Publications">

<@mainLayout title=title>
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
            <button type="submit" class="btn btn-default">Filter</button>
        </form>
    </div>
    <div class="row">
        <div class="col-xs-offset-3 col-xs-6 list-publication">
            <div class="row">
                <div class="col-xs-6">Title</div>
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
                <div class="col-xs-offset-9 col-xs-3">User</div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-offset-3 col-xs-6 list-publication">
            <div class="row">
                <div class="col-xs-6">Title</div>
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
                <div class="col-xs-offset-9 col-xs-3">User</div>
            </div>
        </div>
    </div>
</@mainLayout>