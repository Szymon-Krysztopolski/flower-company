import React, { useState } from "react"

function Rectangle({ src, title, count, onCountChange }) {
    return (
        <div className="rectangle">
            <img src={src} alt={title} />
            <h3>{title}</h3>
            <div className="counter">
                <button onClick={() => onCountChange(title, count > 0 ? count - 1 : 0)}>-</button>
                <span>{count}</span>
                <button onClick={() => onCountChange(title, count + 1)}>+</button>
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
        { src: 'white-rose.jpg', title: 'White rose' },
        { src: 'red-rose.jpg', title: 'Red rose' },
        { src: 'lily.jpg', title: 'Lily' },
        { src: 'iris.jpg', title: 'Iris' },
        { src: 'tulip.jpg', title: 'Tulip' },
    ];

    const [selectedItems, setSelectedItems] = useState(images.map(image => ({ title: image.title, count: 0 })));

    const handleCountChange = (title, newCount) => {
        setSelectedItems(prevItems => prevItems.map(item => item.title === title ? { ...item, count: newCount } : item));
    };

    const handleDelete = (title) => {
        setSelectedItems(prevItems => prevItems.map(item => item.title === title ? { ...item, count: 0 } : item));
    };

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

        if (response.ok) {
            alert('Order placed successfully!');
        } else {
            alert('Failed to place order.');
        }
    };

    const hasItems = selectedItems.some(item => item.count > 0);

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
                {hasItems && <button onClick={handleOrder}>Make Order</button>}
            </div>
        </div>
    );
}
