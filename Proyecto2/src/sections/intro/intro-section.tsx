import React, { useState, useRef } from "react";
//import 'bootswatch/dist/lux/bootstrap.min.css';

type FormElement = React.FormEvent<HTMLFormElement>;
interface ITask {
    name: string;
    done: boolean;
}

export const IntroSection: React.FC = () => {
    const [ newTask, setNewTask] = useState<string>('');
    const [ tasks, setTasks ] = useState<ITask[]>([]);
    const handleSubmit = (e: FormElement) => {
        e.preventDefault();
        addTask(newTask);
        //console.log(newTask);
        //console.log(tasks);
        setNewTask('');
        taskInput.current?.focus();
    }
    
    const taskInput = useRef<HTMLInputElement>(null);

    const toogleDoneTask = (i: number) => {
        const newTasks: ITask[] = [...tasks];
        newTasks[i].done = !newTasks[i].done;
        setTasks(newTasks);
    }

    const removeTask = (i: number) => {
        const newTasks: ITask[] = [...tasks];
        newTasks.splice(i, 1);
        setTasks(newTasks);
    }
    
    const addTask = (name:string) => {
        const newTasks = [...tasks, {name: name, done: false}]
        setTasks(newTasks);
    }

    return (
        <>
        <div className="container p-4">
            <div className="row">
                <div className="col-md-6 offset-md-3">
                    <div className="card">
                        <div className="card-body">
                            <form onSubmit={handleSubmit}>
                                <input type="text" onChange={e => setNewTask(e.target.value)} value={newTask} className="form-control" ref={taskInput} autoFocus />
                                <button className="btn btn-success btn-bloc mt-2">save</button>
                            </form>
                            {
                                tasks.map((t: ITask, i: number) => (
                                    <div className="card card-body mt-2" key={i}>
                                        <h2 style={{textDecoration: t.done ? 'line-through' : ''}}>{t.name}</h2>
                                        <div>
                                            <button className="btn btn-secondary" onClick={() => toogleDoneTask(i)}>
                                                {t.done ? '✓' : '𝒙'}
                                            </button>
                                            <button className="btn btn-danger" onClick={() => removeTask(i)}>
                                            🗑
                                            </button>
                                        </div>
                                    </div>
                                ))
                            }
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </>
    );
}

