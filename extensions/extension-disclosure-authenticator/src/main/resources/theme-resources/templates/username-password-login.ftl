<#import "template.ftl" as layout>
<@layout.registrationLayout displayInfo=false displayMessage=false; section>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="post" action="${url.loginAction}">  <label for="username">Username:</label>
            <input type="text" name="username" id="username" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required>
            <br>
            <button type="submit">Login</button>
            <#if social.providers??>
                <p class="para">${msg("selectAlternative")}</p>
                <div id="social-providers">
                    <#list social.providers as p>
                        <input class="social-link-style" type="button" onclick="location.href='${p.loginUrl}';" value="${p.displayName}"/>
                    </#list>
                </div>
            </#if>



        </form>
    </body>
</html>
</@layout.registrationLayout>