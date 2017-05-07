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