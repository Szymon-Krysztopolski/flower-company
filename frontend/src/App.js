import React from "react"
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import MakeOrder from "./MakeOrder";
import ShowOrders from "./ShowOrders";

export default function App() {
    return (
        <Router>
            <div>
                <nav>
                    <ul>
                        <li>
                            <Link to="/make-order">Make Order</Link>
                        </li>
                        <li>
                            <Link to="/show-orders">Show Orders</Link>
                        </li>
                    </ul>
                </nav>

                <Routes>
                    <Route path="/make-order" element={<MakeOrder />} />
                    <Route path="/show-orders" element={<ShowOrders />} />
                </Routes>
            </div>
        </Router>
    );
}
