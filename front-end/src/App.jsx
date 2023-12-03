import { Routes, Route } from 'react-router-dom';
import Order from './Order';
import Manage from './Manage';

const App = () => <>
  <Routes>
    <Route path="/" element={<Order />} />
    <Route path="/manage" element={<Manage />} />
  </Routes>
</>

export default App
