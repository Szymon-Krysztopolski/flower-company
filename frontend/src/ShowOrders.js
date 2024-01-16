import React, { useState, useEffect } from "react";
import './style.css';
export default function ShowOrders() {
    const [orderIds, setOrderIds] = useState([]);
    const [showDropdown, setShowDropdown] = useState(true);
    const [orders, setOrders] = useState([]);
    const [selectedOrder, setSelectedOrder] = useState(null);
    const [activeOption, setActiveOption] = useState("Selected");

    const fetchOrderIds = async () => {
        const response = await fetch('http://127.0.0.1:8081/api/v1/orders', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const OrderIds = await response.json();
        if (response.ok) {
            setOrderIds(OrderIds);
        }
    };

    useEffect(() => {
        setOrderIds([]);
        fetchOrderIds();
    }, []);

    const fetchAllOrders = async () => {
        const allOrders = [];
        for (const orderId of orderIds) {
            const response = await fetch(`http://127.0.0.1:8081/api/v1/status/${orderId}`);
            if (response.ok) {
                const jsonResponse = await response.json();
                allOrders.push({
                    id: jsonResponse.id,
                    status: jsonResponse.code,
                    price: jsonResponse.price,
                    isOpen: true
                });
            }
        }
        setOrders(allOrders);
        setShowDropdown(false);
        setSelectedOrder(null);
        setActiveOption("All");
    };

    const handleSelectChange = async (event) => {
        const orderId = event.target.value;
        if (orderId === "") {
            setSelectedOrder(null);
            return;
        }
        const response = await fetch(`http://127.0.0.1:8081/api/v1/status/${orderId}`);
        if (response.ok) {
            const jsonResponse = await response.json();
            setSelectedOrder({
                id: jsonResponse.id,
                status: jsonResponse.code,
                price: jsonResponse.price,
                isOpen: true
            });
            setOrders([]);
        }
    };

    return (
        <div className="dropdown-container">
            <h2>Show Orders</h2>
            <div className="orders">
                <button className={activeOption === "All" ? "active" : ""} onClick={fetchAllOrders}>All</button>
                <button className={activeOption === "Selected" ? "active" : ""} onClick={() => {setShowDropdown(true); setOrders([]); setActiveOption("Selected");}}>Selected</button>
            </div>
            {showDropdown && (
                <select onChange={handleSelectChange}>
                    <option value="">Select an order</option>
                    {orderIds.map((order, index) => 
                        <option key={index} value={order}>{order}</option>
                    )}
                </select>
            )}
            {selectedOrder && (
                <table>
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Status</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{selectedOrder.id}</td>
                            <td>{selectedOrder.status}</td>
                            <td>{selectedOrder.price}</td>
                        </tr>
                    </tbody>
                </table>
            )}
            {orders.length > 0 && (
                <table>
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Status</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        {orders.map((order, index) => 
                            <tr key={index}>
                                <td>{order.id}</td>
                                <td>{order.status}</td>
                                <td>{order.price}</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            )}
        </div>
    );
}
