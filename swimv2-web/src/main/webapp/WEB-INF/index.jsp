<%@ page language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
    <head>
        <title>SWIM version 2</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        
        <!-- TODO to be moved in a separate .css file -->
        <style type="text/css">
            #swim-general-header {
                margin-top: 2em;
                margin-bottom: 1em;
            }
        </style>
    </head>
    <body>
        <div class="container">
        
            <div id="swim-general-header">
                <h3 class="muted">SWIMv2</h3>
            </div>
        
            <div class="row">
                <div class="span7 hero-unit">
                    <h1>SWIMv2</h1>
                    <p>The Small World Hypotesis Machine, reloaded</p>
                    <h2>Search for help!</h2>
                    <p>What kind of ability are you looking for?</p>
                    <form class="input-prepend input-append">
                    <span class="add-on"><i class="icon-search"></i></span>
                    <input id="abilities" class="span5" type="text" placeholder="cooker, plumber"> 
                    <button type="submit" class="btn btn-primary">Find help!</button>
                    </form>
                </div>
                <div class="span3">
                    <ul class="nav nav-tabs">
                        <li <c:if test="${!toggleRegistration}">
                            class="active"
                        </c:if> ><a href="#login" data-toggle="tab">Login</a></li>
                        <li <c:if test="${toggleRegistration}">
                                class="active"
                            </c:if>
                        ><a href="#register" data-toggle="tab">Register</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="login">
                            <form method="post">
                              <c:if test="${wrongLogin}">
                                  <div class="alert alert-error">Wrong username or password</div>
                              </c:if>
                              <fieldset>
                                <input type="text" name="user" placeholder="Username">
                                <input type="password" name="password" placeholder="Password">
                                <button type="submit" class="btn">Submit</button>
                              </fieldset>
                            </form>
                        </div>
                        <div class="tab-pane" id="register">
                            <form method="post">
                              <c:if test="${wrongRegistration}">
                                  <div class="alert alert-error">Error while registering user</div>
                              </c:if>
                              <fieldset>
                                <input type="text" name="email" placeholder="E-mail address">
                                <input type="password" name="password" placeholder="Password">
                                <span class="help-block">We'll send you an email with the instruction to complete the registration</span>
                                <button type="submit" class="btn">Register</button>
                              </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- TODO include locally jquery, we don't want to depend upon external stuff! -->
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
