import { useEffect, useState } from 'react';
import { getItems, itemPath } from './itemService';
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

    const deleteItem = id => axios.delete(`${itemPath}/${id}`).then(
        _ => setItems(items.filter(item => item.id != id))
    ).catch(() => alert(`Problems deleting ${items.find(item => item.id === id).name}!`));

    useEffect(() => {
        getItems().then(response => response.json())
            .then(actualData => setItems(toInternalCentPrices(actualData)))
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
