import React, { useState, useEffect } from "react"

function Rectangle({ src, title, onCountChange }) {
    const [count, setCount] = useState(0);

    useEffect(() => {
        onCountChange(title, count);
    }, [count]);

    return (
        <div className="rectangle">
            <img src={src} alt={title} />
            <h3>{title}</h3>
            <div className="counter">
                <button onClick={() => setCount(count > 0 ? count - 1 : 0)}>-</button>
                <span>{count}</span>
                <button onClick={() => setCount(count + 1)}>+</button>
            </div>
        </div>
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

    const [selectedItems, setSelectedItems] = useState([]);

    const handleCountChange = (title, count) => {
        setSelectedItems(prevItems => {
            const existingItem = prevItems.find(item => item.title === title);
            if (existingItem) {
                if (count === 0) {
                    return prevItems.filter(item => item.title !== title);
                } else {
                    return prevItems.map(item => item.title === title ? { ...item, count } : item);
                }
            } else {
                return [...prevItems, { title, count }];
            }
        });
    };

    return (
        <div className="container">
            {images.map((image, index) => (
                <Rectangle key={index} src={image.src} title={image.title} onCountChange={handleCountChange} />
            ))}
            <div className="rectangle summary">
                <h2>Summary</h2>
                {selectedItems.filter(item => item.count > 0).map((item, index) => (
                    <p key={index}>{item.title}: {item.count}</p>
                ))}
            </div>
        </div>
    );
}
