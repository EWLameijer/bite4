import { useEffect, useState } from 'react';
import { deleteItemById, getItems, itemPath } from './itemService';
import AddItem from './AddItem';
import { formatAsPrice, toInternalCentPrices, toInternalPrice } from './utils';
import { Link } from 'react-router-dom'
import axios from 'axios';

const Manage = () => {
    const [items, setItems] = useState([]);

    const addItem = item => {
        console.log(item);
        item.price = item.price.replace(',', '.');
        axios.post(itemPath, item).then(response => {
            const newItem = toInternalPrice(response.data);
            setItems([...items, newItem]);
        })
    }

    const deleteItem = id => deleteItemById(id).then(
        _ => setItems(items.filter(item => item.id != id))
    );

    useEffect(() => {
        getItems().then(response => response.json())
            .then(actualData => {
                console.table(actualData);
                const result = toInternalCentPrices(actualData)
                console.table(result)
                setItems(result)
            })
            .catch(err => console.log(`An error has occurred: ${err.message}.`))
    }, []);

    return <>
        <p>Manage!</p>
        <Link to="/">Order now...</Link>
        <ol>
            {items.map(item => <li key={item.id}>{item.name} {formatAsPrice(item.price)}<button onClick={() => deleteItem(item.id)}>Delete</button></li>)}
        </ol>
        <AddItem addItem={addItem} />
    </>
}

export default Manage;
