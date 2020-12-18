import { Route, Switch, BrowserRouter } from "react-router-dom";
import Home from "./component/home/Home";
import Login from "./component/Authentication/loginForm/Login"
import PrivateRoute from "./routes/privateRoute/PrivateRoute"

function App() {

  return (

    <BrowserRouter>
      <Switch>
        <Route path='/login' component={Login} />
        <PrivateRoute path='/' component={Home} />
        {/* <Route component={Page404} /> */}
      </Switch>
    </BrowserRouter>
  );
}

export default App;
