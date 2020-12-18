import { Route, Switch, BrowserRouter } from "react-router-dom";
import Home from "./component/home/Home";
import Login from "./component/Authentication/loginForm/Login"
import PrivateRoute from "./routes/privateRoute/PrivateRoute"
import { Suspense } from "react";
import Spinner from "./component/shared/spinner/Spinner";

function App() {

  return (

    <Suspense fallback={<Spinner />}>
      <BrowserRouter>
        <Switch>
          <Route path='/login' component={Login} />
          <PrivateRoute path='/' component={Home} />
          {/* <Route component={Page404} /> */}
        </Switch>
      </BrowserRouter>
    </Suspense>
  );
}

export default App;
