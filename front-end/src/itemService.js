import { postData } from './utils';
import bite from './bite';

const itemPath = `${bite.basePath}items`;

const post = item => postData(itemPath, item);

const getItems = () => fetch(itemPath);

export { getItems, post, itemPath }