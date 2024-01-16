import React, { useState } from "react"
function Modal({ isOpen, orderStatus, orderId, onClose }) {
    if (!isOpen) return null;
    return (
        <div className="modal">
            <div className="modal-content">
                {orderStatus === 'success' && <strong>Order placed successfully!</strong>}
                {orderStatus === 'failure' && <strong>Failed to place order.</strong>}
                {orderStatus === 'success' && <div>Your order has number: <strong>{orderId}</strong></div>}
                <button onClick={onClose}>Close</button>
            </div>
        </div>
    );
}


function Rectangle({ src, title, count, onCountChange }) {
    return (
        <div className="rectangle">
            <img src={src} alt={title} />
            <div className="rectangle-row">
                <div className="rectangle-title">{title}</div>
                <div className="counter">
                    <button onClick={() => onCountChange(title, count > 0 ? count - 1 : 0)}>-</button>
                    <span>{count}</span>
                    <button onClick={() => onCountChange(title, count + 1)}>+</button>
                </div>
            </div>
            
        </div>
    );
}
function SummaryItem({ title, count, onCountChange, onDelete }) {
    return (
        <tr>
            <td>{title}</td>
            <td>
                <button onClick={() => onCountChange(title, count > 0 ? count - 1 : 0)}>-</button>
                {count}
                <button onClick={() => onCountChange(title, count + 1)}>+</button>
            </td>
            <td>
            <button className="delete-button" onClick={() => onDelete(title)}>Delete</button>
            </td>
        </tr>
    );
}
export default function MakeOrder() {
    const images = [
        { src: 'white-rose.jpg', title: 'WHITE_ROSE' },
        { src: 'red-rose.jpg', title: 'RED_ROSE' },
        { src: 'lily.jpg', title: 'LILY' },
        { src: 'iris.jpg', title: 'IRIS' },
        { src: 'tulip.jpg', title: 'TULIP' },
    ];
    const [selectedItems, setSelectedItems] = useState(images.map(image => ({ title: image.title, count: 0 })));
    const handleCountChange = (title, newCount) => {
        setSelectedItems(prevItems => prevItems.map(item => item.title === title ? { ...item, count: newCount } : item));
    };
    const handleDelete = (title) => {
        setSelectedItems(prevItems => prevItems.map(item => item.title === title ? { ...item, count: 0 } : item));
    };
    const [modalOpen, setModalOpen] = useState(false);

    const [orderStatus, setOrderStatus] = useState('');
    const [orderId, setOrderId] = useState('');
    const handleOrder = async () => {
        const order = {
            bouquet: selectedItems.filter(item => item.count > 0).map(item => ({
                flower: item.title,
                amount: item.count
            }))
        };
        const response = await fetch('http://127.0.0.1:8081/api/v1/order', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(order)
        });
        const OrderId = await response.text();

        if (response.ok) {
            setOrderStatus('success');
        } else {
            setOrderStatus('failure');
        }
        setOrderId(OrderId);
        setModalOpen(true);
    };
    const hasItems = selectedItems.some(item => item.count > 0);

    const [orders, setOrders] = useState([]);

    const closeModal = async () => {
        setModalOpen(false);
        setSelectedItems(images.map(image => ({ title: image.title, count: 0 })));
    };
    

    return (
        <div className="container">
            {selectedItems.map((item, index) => (
                <Rectangle key={index} src={images.find(image => image.title === item.title).src} title={item.title} count={item.count} onCountChange={handleCountChange} onDelete={handleDelete} />
            ))}
            <div className="rectangle summary">
                <h2>Summary</h2>
                <table>
                    {selectedItems.filter(item => item.count > 0).map((item, index) => (
                        <SummaryItem key={index} title={item.title} count={item.count} onCountChange={handleCountChange} onDelete={handleDelete} />
                    ))}
                </table>
                <div>
                    {hasItems && <button className="make-order-button" onClick={handleOrder}>Make Order</button>}
                </div>
                
            </div>
            <Modal isOpen={modalOpen} orderStatus={orderStatus} orderId={orderId} onClose={closeModal} />

        </div>
    );
}
