# JUnit Support Classes

## BrowserTest

This class sets some useful convenience methods for our Selenide test.

### Chrome Session Management

    startHeadlessChrome()
    stopChrome()

The `startHeadlessChrome()` method starts a headless Chrome session automatically,
as long as we aren't running the test in debug mode.

Selenide is actually perfectly capable of starting and stopping a Chrome session
for us automatically, and will do so in any test it's referenced in by default.

Because we've taken manual control of the Chrome session, though, we have to
explicitly stop it again with the `stopChrome()` method.  If we didn't, Chrome 
would be left still running after our tests finish.