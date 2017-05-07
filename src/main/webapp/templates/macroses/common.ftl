<#assign springSecurity=JspTaglibs["http://www.springframework.org/security/tags"]>

<#macro sourceOwner user ownContent commonContent>
    <@springSecurity.authorize access="hasRole('ROLE_USER')">
        <@springSecurity.authentication property="principal" var="currentUser"/>

        <#if currentUser.equals(user)>
            ${ownContent}
        <#else>
            ${commonContent}
        </#if>
    </@springSecurity.authorize>
    <@springSecurity.authorize access="isAnonymous()">
        ${commonContent}
    </@springSecurity.authorize>
</#macro>

<#function addSearchParams>
    <#assign searchParams="">
    <#if RequestParameters.user??>
        <#assign searchParams=searchParams+"&user="+RequestParameters.user>
    </#if>
    <#if RequestParameters.topic??>
        <#assign searchParams=searchParams+"&topic="+RequestParameters.topic>
    </#if>
    <#if RequestParameters.onlyMine??>
        <#assign searchParams=searchParams+"&onlyMine="+RequestParameters.onlyMine>
    </#if>

    <#return searchParams>
</#function>