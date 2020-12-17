import 'antd/dist/antd.css';


import { Layout } from 'antd';
import Login from './component/Authentication/loginForm/Login';
function App() {
  const { Content } = Layout;

  return (
    <Layout>
      <Content>
        <Login />
      </Content>
    </Layout>
  );
}

export default App;
