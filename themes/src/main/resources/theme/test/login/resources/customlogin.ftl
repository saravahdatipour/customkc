<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${url.resourcesPath}/css/bootstrap.min.css">
    <script src="${url.resourcesPath}/js/bundle.js"></script>
    <style>
        html, body {
            margin: 10px 0;
            padding: 0;
            width: 100%;
        }
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        body > * {
            padding: 10px;
            margin: 10px;
        }
    </style>
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
        <button id="start-popup">Start Yivi Popup</button>
        <script type="text/javascript">
            document.getElementById('start-popup').onclick = () => {
                let yiviPopup = yivi.newPopup(options); // Ensure options are defined in your main JS code
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
