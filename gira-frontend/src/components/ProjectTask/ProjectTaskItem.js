import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { deleteProjectTask } from "../../actions/projectTaskActions";
import '../../styles.css';
//classNames 
 class ProjectTaskItem extends Component {
    onDeleteClic(pt_id){
        this.props.deleteProjectTask(pt_id);
    }

  render() {
    const {project_task} = this.props;
    const { priority } = project_task;
    return (
        <div className="card mb-1 bg-light">
        <div className="card-header text-primary">
            ID: {project_task.id}
        </div>
        <div className="card-body bg-light">
        
            <h5 className="card-title">{project_task.summary}</h5>
            <p className="card-text text-truncate ">
                {project_task.acceptanceCritteria}
            </p>

            <p><small className={`priority priority-${priority}`}>{project_task.priority}</small></p>
            <Link to={`updateProjectTask/${project_task.id}`} className="btn btn-primary">
                View / Update
            </Link>
            <button className="btn btn-danger ml-4" onClick={this.onDeleteClic.bind(this,project_task.id)}>
                Delete
            </button>
        </div>
    </div>
    )
  }
}

ProjectTaskItem.PropTypes = {
    deleteProjectTask: PropTypes.func.isRequired
}

export default connect(null, {deleteProjectTask}) (ProjectTaskItem);