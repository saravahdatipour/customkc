
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="post" action="${url.loginAction}">
            <label for="username">Username:</label>
            <input type="text" name="username" id="username" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required>
            <br>
            <button type="submit">Login</button>

            <!-- Social providers section -->
            <#if social.providers??>
                <p class="para">${msg("selectAlternative")}</p>
                <div id="social-providers">
                    <#list social.providers as p>
                        <input class="social-link-style" type="button" onclick="location.href='${p.loginUrl}';" value="${p.displayName}"/>
                    </#list>
                </div>
            </#if>

            <!-- Yivi Popup Button -->
            <script src="${url.resourcesPath}/theme-resources/js/bundle.js"></script>
            <button id="start-popup">Start Yivi Popup</button>

            <script type="text/javascript">
                document.getElementById('start-popup').onclick = () => {
                    let yiviPopup = yivi.newPopup(options); // Ensure options are defined as in your main JS code
                    yiviPopup.start()
                        .then(result => console.log("Successful disclosure! 🎉", result))
                        .catch(error => {
                            if (error === 'Aborted') {
                                console.log('We closed it ourselves, so no problem 😅');
                                return;
                            }
                            console.error("Couldn't do what you asked 😢", error);
                        })
                        .finally(() => yiviPopup = yivi.newPopup(options));
                };
            </script>
        </form>
    </body>
</html>

