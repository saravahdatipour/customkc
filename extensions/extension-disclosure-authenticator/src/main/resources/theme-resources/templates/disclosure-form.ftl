<#import "template.ftl" as layout>
<@layout.registrationLayout displayInfo=social.displayInfo; section>
    <#if section == "title">
        ${msg("loginTitle",(realm.displayName!''))}
    <#elseif section == "header">
        <link href="https://fonts.googleapis.com/css?family=Muli" rel="stylesheet"/>
        <link href="${url.resourcesPath}/img/favicon.png" rel="icon"/>
        <script>
            function togglePassword() {
                var x = document.getElementById("password");
                var v = document.getElementById("vi");
                if (x.type === "password") {
                    x.type = "text";
                    v.src = "${url.resourcesPath}/img/eye.png";
                } else {
                    x.type = "password";
                    v.src = "${url.resourcesPath}/img/eye-off.png";
                }
            }
        </script>
    <#elseif section == "form">
        <div class="login-page-wrapper">
            <div class="login-box">
                <img class="logo" src="${url.resourcesPath}/img/alfresco-logo.svg" alt="Alfresco">
                <p class="application-name">Alfresco Identity Service</p>
                <#if realm.password>
                    <form id="kc-form-login" class="form" action="${url.loginAction}" method="post">
                        <div class="form-group">
                            <input id="username" class="form-control" placeholder="${msg("username")}" type="text" name="username" required tabindex="1">
                            <div class="password-toggle">
                                <input id="password" class="form-control" placeholder="${msg("password")}" type="password" name="password" required tabindex="2">
                                <span class="toggle" onclick="togglePassword()">
                                    <img id="vi" src="${url.resourcesPath}/img/eye-off.png" alt="Toggle Password Visibility">
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <input class="btn btn-primary" type="submit" value="${msg("doLogIn")}" tabindex="3">
                        </div>
                    </form>
                </#if>
                <#if social.providers??>
                    <div class="social-providers">
                        <p>${msg("selectAlternative")}</p>
                        <#list social.providers as p>
                            <a class="btn-social" href="${p.loginUrl}">${p.displayName}</a>
                        </#list>
                    </div>
                </#if>
                <p class="copyright">&copy; ${msg("copyright", "${.now?string('yyyy')}")}</p>
            </div>
        </div>
    </#if>
</@layout.registrationLayout>
