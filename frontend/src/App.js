import React from "react"
import { BrowserRouter as Router, Route, Routes, NavLink } from "react-router-dom";
import MakeOrder from "./MakeOrder";
import ShowOrders from "./ShowOrders";

export default function App() {
    return (
        <Router>
            <div>
                <nav>
                    <ul>
                        <li>
                            <NavLink to="/" activeClassName="active">Make Order</NavLink>
                        </li>
                        <li>
                            <NavLink to="/show-orders" activeClassName="active">Show Orders</NavLink>
                        </li>
                    </ul>
                </nav>

                <Routes>
                    <Route path="/" element={<MakeOrder />} />
                    <Route path="/show-orders" element={<ShowOrders />} />
                </Routes>
            </div>
        </Router>
    );
}
